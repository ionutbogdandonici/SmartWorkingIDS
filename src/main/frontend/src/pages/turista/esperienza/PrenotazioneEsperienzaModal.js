import { Dialog, Transition } from "@headlessui/react";
import { Formik, Form, Field } from "formik";
import React, { useRef, Fragment, useEffect, useState } from "react";
import Button from "../../../components/Button";
import axios from "axios";
import * as yup from "yup";

function PrenotazioneEsperienzaModal({ show, setShow, esperienza }) {
    const [open, setOpen] = useState(false);
    const [user, setUser] = useState({});

    axios.defaults.headers.common["Authorization"] = `Bearer ${sessionStorage.getItem("Access-Token")}`;

    const initialValues = {
        postiDaRiservare: 1,
    };

    const validationSchema = yup.object().shape({
        postiDaRiservare: yup.number().required("Numero posti da riservare is required"),
    });

    const cancelButtonRef = useRef(null);

    useEffect(() => {
        setOpen(show);
        const username = sessionStorage.getItem("Username");
        axios
            .get("http://localhost:8080/turisti/getByUsername", {
                params: {
                    username: username,
                },
            })
            .then((res) => {
                setUser(res.data);
            });
        axios.get("http://localhost:8080/prenotazioni/getPrenotazioneById/1").then((res) => {
            console.log(res.data);
        });
    }, [show]);

    const closeModal = () => {
        setShow(false);
    };

    const prenota = (data) => {
        const prenotazione = {
            idEsperienza: esperienza.id,
            idTurista: user.idTurista,
            numeroPosti: data.postiDaRiservare,
            stato_pagamento: "IN_ATTESA",
        };

        axios
            .post(`http://localhost:8080/esperienze/prenotaEsperienza/${esperienza.id}`, user)
            .then((res) => {
                console.log(res);
                closeModal();
            });
    };

    return (
        <Transition.Root show={open}>
            <Dialog as="div" className="relative" initialFocus={cancelButtonRef} onClose={setShow}>
                <Transition.Child as={Fragment} enter="ease-out duration-300" enterFrom="opacity-0" enterTo="opacity-100" leave="ease-in duration-200" leaveFrom="opacity-100" leaveTo="opacity-0">
                    <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
                </Transition.Child>
                <div className="fixed z-10 inset-0 overflow-y-auto">
                    <div className="flex items-end sm:items-center justify-center min-h-full p-4 text-center sm:p-0">
                        <Transition.Child as={Fragment} enter="ease-out duration-300" enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95" enterTo="opacity-100 translate-y-0 sm:scale-100" leave="ease-in duration-200" leaveFrom="opacity-100 translate-y-0 sm:scale-100" leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95">
                            <Dialog.Panel className="relative overflow-visible transform transition-all sm:my-8 sm:max-w-4xl sm:w-full">
                                <div className="bg-white shadow sm:rounded-lg text-left">
                                    <div className="px-4 py-5 sm:px-6">
                                        <h3 className="text-xl leading-6 font-medium text-zinc-800">Prenotazione Esperienza</h3>
                                        <p className="mt-1 max-w-2xl text-sm text-gray-500">
                                            Dettagli
                                            <span className="font-semibold text-gray-800">{" " + esperienza.nome}</span>
                                        </p>
                                    </div>
                                    <div className="border-y">
                                        <dl>
                                            <div className="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                                                <dt className="text-sm font-medium text-gray-500">Nome dell'Esperienza</dt>
                                                <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{esperienza.nome}</dd>
                                            </div>
                                            <div class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                                                <dt class="text-sm font-medium text-gray-500">Descrizione</dt>
                                                <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{esperienza.descrizione}</dd>
                                            </div>
                                            <div className="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                                                <dt className="text-sm font-medium text-gray-500">Area</dt>
                                                <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{esperienza.area.toponimo}</dd>
                                            </div>
                                            <div class="bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                                                <dt class="text-sm font-medium text-gray-500">Percorso proposto</dt>
                                                <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{esperienza.percorso.nome}</dd>
                                            </div>
                                            <div className="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                                                <dt className="text-sm font-medium text-gray-500">Posti Disponibili</dt>
                                                <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{esperienza.postiMax - esperienza.postiRiservati}</dd>
                                            </div>
                                        </dl>
                                    </div>
                                    <div class="py-4 px-4">
                                        <div class="">
                                            <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={prenota}>
                                                <Form>
                                                    <div className="flex justify-between">
                                                        <div className="flex flex-row items-center">
                                                            <label htmlFor="postiDaRiservare" className="block text-sm font-medium text-gray-700 mr-2">
                                                                Numero Posti da Riservare
                                                            </label>
                                                            <Field id="postiDaRiservare" name="postiDaRiservare" className="mt-1 block w-full pl-3 pr-10 py-2 text-base border border-gray-300 focus:outline-none focus:ring-green-500 focus:border-green-500 sm:text-sm rounded-md" defaultValue="1" component="select">
                                                                <option>1</option>
                                                                <option>2</option>
                                                                <option>3</option>
                                                                <option>4</option>
                                                                <option>5</option>
                                                                <option>6</option>
                                                                <option>7</option>
                                                            </Field>
                                                        </div>
                                                        <div className="space-x-2">
                                                            <Button text="Annulla" type="button" decoration="secondary" onClick={closeModal} />
                                                            <Button text="Prenota" type="submit" decoration="primary" />
                                                        </div>
                                                    </div>
                                                </Form>
                                            </Formik>
                                        </div>
                                    </div>
                                </div>
                            </Dialog.Panel>
                        </Transition.Child>{" "}
                    </div>
                </div>
            </Dialog>
        </Transition.Root>
    );
}

export default PrenotazioneEsperienzaModal;
