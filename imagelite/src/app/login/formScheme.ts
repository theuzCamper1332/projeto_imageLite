import * as Yup from 'yup'

export interface LoginForm {
    name?: string;
    email: string;
    password: string;
    passwordMath?: string;
}

export const validationScheme = Yup.object().shape({
    email: Yup.string().trim().required('Email is Required').email('Invalid Email'),
    password: Yup.string().required('Password is required').min(8,'Password must have at least 8 charecters'),
    passwordMath: Yup.string().oneOf([Yup.ref('password')], 'Password must match')
})

export const formScheme: LoginForm = {email: '', name: '', password: '', passwordMath: ''}