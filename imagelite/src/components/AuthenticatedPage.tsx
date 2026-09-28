import {useAuth} from '@/resources'
import Login from '@/app/login/page'

interface AuthenticatedPagesProps{
    children: React.ReactNode
}

export const AuthenticatedPage: React.FC<AuthenticatedPagesProps> = ({
    children
}) => {

    const auth = useAuth();

    if(!auth.isSessionValid()){
        return <Login />
    }

    return (
        <>
            {children}
        </>
    )
}