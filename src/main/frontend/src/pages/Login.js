import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { Formik, Form } from "formik";
import * as yup from "yup";
import axios from "axios";
import Button from "../components/Button";
import InputField from "../components/InputField";

function Login() {

    const navigator = useNavigate();

    const initailValues = {
        username: "",
        password: "",
    };


    const validationSchema = yup
        .object({
            username: yup.string().required("Username is required"),
            password: yup.string().required("Password is required"),
        })
        .defined();

    const handleSubmit = (data) => {
        axios.post("http://localhost:8080/login", data).then((res) => {
            if(res.status === 200) {
                sessionStorage.setItem("Access-Token", res.data.token);
                sessionStorage.setItem("Username", res.data.username);
                navigator("/turista/home");
            }
        });
    };

    return (
        <div className="container mx-auto flex flex-col lg:grid lg:grid-cols-3 p-4 font-inter">
            <div className="col-start-2 col-end-3 mt-16">
                <div className="text-center mb-16">
                    <Link to="/">
                        <h1 className="text-4xl font-bold text-zinc-800">Cicerone</h1>
                    </Link>
                    <p className="mt-1 text-zinc-500 font-normal">Progetto sviluppato dal Gruppo Smart Working</p>
                </div>
                <Formik className="flex flex-col" initialValues={initailValues} validationSchema={validationSchema} onSubmit={handleSubmit}>
                    <Form>
                        <InputField placeholder="Please insert your username" name="username" type="input" label="Username" />
                        <InputField placeholder="Please insert your password" name="password" type="password" label="Password" />
                        <div className="mt-2 mb-12">
                            <Link to="/login/forgotPassword" className="text-xs text-neutral-500">
                                Forgot password?
                            </Link>
                        </div>
                        <div>
                            <Button decoration="primary" type="submit" otherCSS={"w-full"} text="Login" />
                            <div className="mt-2">
                                <p className="text-xs text-neutral-600 text-center mb-2">Don't have an account yet?</p>
                                <Link to="/registrazioneTurista">
                                    <Button decoration="secondary" type="button" otherCSS={"w-full"} text="Register" />
                                </Link>
                            </div>
                        </div>
                    </Form>
                </Formik>
            </div>
        </div>
    );
}

export default Login;
