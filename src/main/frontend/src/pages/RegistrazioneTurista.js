import React, { useEffect } from "react";
import axios from "axios";
import { Formik, Form } from "formik";
import * as yup from "yup";
import { Link } from "react-router-dom";
import InputField from "../components/InputField";
import Button from "../components/Button";

function RegistrazioneTurista() {
    const initialValues = {
        username: "",
        nome: "",
        cognome: "",
        numeroTelefonico: "",
        residenza: "",
        email: "",
        password: "",
    };

    const validationSchema = yup.object().shape({
        username: yup.string().required("Username is required"),
        nome: yup.string().required("Nome is required"),
        cognome: yup.string().required("Cognome is required"),
        numeroTelefonico: yup.string().required("Numero telefonico is required"),
        residenza: yup.string().required("Residenza is required"),
        email: yup.string().email("Email isn't in correct format").required("Email is required"),
        password: yup.string().required("Password is required"),
    });

    const onSubmit = (data) => {
        axios.post("http://localhost:8080/newTurista", data).then((res) => {
            console.log(res);
        })
    };

    return (
        <div className="container mx-auto flex flex-col p-4">
            <div className="col-start-2 col-end-5">
                <div className="mt-16">
                    <Link to="/" className="text-4xl font-bold text-zinc-800">
                        Registrazione Turista
                    </Link>
                    <p className="mt-1 text-zinc-500 font-normal">Progetto sviluppato dal Gruppo Smart Working</p>
                </div>
            </div>
            <Formik className="flex flex-col" initialValues={initialValues} validationSchema={validationSchema} onSubmit={onSubmit}>
                <Form>
                    <div className="pb-6 my-12">
                        <div className="flex flex-col lg:flex-row lg:space-x-8">
                            <InputField name="nome" label="Nome" type="text" placeholder="Nome turista" attributes="w-full" />
                            <InputField name="cognome" label="Cognome" type="text" placeholder="Cognome turista" attributes="w-full" />
                        </div>
                        <div className="flex flex-col lg:flex-row lg:space-x-8">
                            <InputField name="username" label="Username" type="text" placeholder="Username turista" attributes="w-full" />
                            <InputField name="email" label="Email" type="email" placeholder="Email turista" attributes="w-full" />
                            <InputField name="numeroTelefonico" label="Numero telefonico" type="text" placeholder="Numero telefonico turista" attributes="w-full" />
                        </div>
                        <InputField name="residenza" label="Residenza" type="text" placeholder="Inserire la via ed il CAP" attributes="w-full" />
                        <div className="flex flex-col lg:flex-row lg:space-x-8">
                            <InputField name="password" label="Password" type="password" placeholder="Password" attributes="w-full" />
                            <InputField name="passwordConfirmation" label="Conferma password" type="password" placeholder="Conferma password" attributes="w-full" />
                        </div>
                    </div>
                    <div className="mt-16">
                    <Button decoration="primary" type="submit" otherCSS={"w-full"} text="Registrati ora!" />
                        <div className="mt-2">
                            <p className="text-xs text-neutral-600 text-center mb-2">Hai già un'account?</p>
                            <Link to="/login">
                                <Button decoration="secondary" type="button" otherCSS={"w-full"} text="Login"/>
                            </Link>
                        </div>
                    </div>
                </Form>
            </Formik>
        </div>
    );
}

export default RegistrazioneTurista;
