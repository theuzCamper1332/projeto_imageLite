import {AccessToken, Credentials, User, UserSessionToken} from './user.resource'
import jwt from 'jwt-decode'

class AuthService {
    baseURL: string = process.env.NEXT_PUBLIC_API_URL + '/v1/users';
    static AUTH_PARAN: string = "_auth";

    async authenticate(credentials: Credentials) : Promise<AccessToken> {
        const response = await fetch(this.baseURL + "/auth", {
            method:'POST',
            body: JSON.stringify(credentials),
            headers: {
                "Content-Type": "application/json"
            }
        });

        if(response.status == 401){
            throw new Error("User or passoword are incorrect!")
        }

        return await response.json();
    }

    async save(user: User) : Promise<void>{
        const response = await fetch(this.baseURL, {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        });

        if(response.status == 409){
            const responseError = await response.json();
            throw new Error(responseError.error);
        }
    }

    initSession(token: AccessToken){
        if(token.accessToken){
            const decodedToken: any = jwt(token.accessToken);

            const userSessionToken: UserSessionToken = {
                accessToken: token.accessToken,
                email: decodedToken.sub,
                name: decodedToken.name,
                expiration: decodedToken.exp
            }
            this.setUserSession(userSessionToken);
        }
    }

    setUserSession(userSessionToken: UserSessionToken){
        try{
        localStorage.setItem(AuthService.AUTH_PARAN, JSON.stringify(userSessionToken));
        }catch(error){}
    }

    getUserSession() : UserSessionToken | null {
        try{
        const authString = localStorage.getItem(AuthService.AUTH_PARAN);
        if(!authString){
            return null;
        }

        const token : UserSessionToken = JSON.parse(authString);
        return token;
        }catch(error){
            return null;
        }
    }

    isSessionValid() : boolean {
        const userSession : UserSessionToken | null = this.getUserSession();
        if(!userSession){
            return false;
        }

        const expiration : number | undefined = userSession.expiration;
        if(expiration){
            const expirationDateMillis = expiration * 1000;
            console.log("Data Expiracao", new Date(expirationDateMillis));
            return new Date() < new Date(expirationDateMillis);
        }

        return false;
    }

    invalideteSession(): void {
        try{
            localStorage.removeItem(AuthService.AUTH_PARAN)
        }catch(error){}
    }
}

export const useAuth = () => new AuthService();