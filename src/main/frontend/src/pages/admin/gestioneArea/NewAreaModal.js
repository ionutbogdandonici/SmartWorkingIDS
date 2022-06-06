import React, { useState, useRef, Fragment, useEffect } from "react";
import { Dialog, Transition } from "@headlessui/react";
import { Formik, Form } from "formik";
import * as yup from "yup";
import axios from "axios";
import Button from "../../../components/Button";
import InputField from "../../../components/InputField";
import SelectFieldArea from "../../../components/SelectFieldArea";

function NewAreaModal({ show, setShow }) {
    const [territori, setTerritori] = useState([]);

    const [open, setOpen] = useState(false);

    const cancelButtonRef = useRef(null);

    useEffect(() => {
        axios.get("http://localhost:8080/territori/all").then((res) => {
            setTerritori(res.data);
        });
        setOpen(show);
    }, [show]);

    const initialValues = {
        toponimo: "",
        territorio: "",
    };

    const validationSchema = yup
        .object({
            toponimo: yup.string().required("Il toponimo è obbligatorio"),
            territorio: yup.string().required("Il territorio è obbligatorio"),
        })
        .defined();

    const handleCreaArea = (values) => {
        console.log("Bella!")
        console.log(values);
    };

    return (
        <Transition.Root show={open}>
            <Dialog as="div" className="relative z-10" initialFocus={cancelButtonRef} onClose={setShow}>
                <Transition.Child as={Fragment} enter="ease-out duration-300" enterFrom="opacity-0" enterTo="opacity-100" leave="ease-in duration-200" leaveFrom="opacity-100" leaveTo="opacity-0">
                    <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
                </Transition.Child>
                <div className="fixed z-10 inset-0 overflow-y-auto">
                    <div className="flex items-end sm:items-center justify-center min-h-full p-4 text-center sm:p-0">
                        <Transition.Child as={Fragment} enter="ease-out duration-300" enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95" enterTo="opacity-100 translate-y-0 sm:scale-100" leave="ease-in duration-200" leaveFrom="opacity-100 translate-y-0 sm:scale-100" leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95">
                            <Dialog.Panel className="relative bg-white rounded-lg text-left overflow-visible shadow-xl transform transition-all sm:my-8 sm:max-w-lg sm:w-full">
                                <div className="px-4 py-3 border-b border-gray-200">
                                    <h1 className="font-semibold text-xl text-gray-900">Creazione Nuova Area</h1>
                                </div>
                                <Formik validationSchema={validationSchema} initialValues={initialValues} onClick={handleCreaArea}>
                                    <Form>
                                        <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4 sm:pt-0">
                                            <InputField placeholder="Inserire toponimo" name="toponimo" type="text" label="Toponimo" />
                                            <SelectFieldArea label="Territorio" name="territorio" values={territori} />
                                        </div>
                                        <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse rounded-b-lg">
                                            <Button type="submit" text="Crea Area" decoration="primary" otherCSS={"w-full justify-center sm:ml-3 sm:w-auto sm:text-sm"}  />
                                            <Button type="button" text="Annulla" decoration="secondary" otherCSS={"w-full justify-center sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"} onClick={() => setShow(false)} ref={cancelButtonRef} />
                                        </div>
                                    </Form>
                                </Formik>
                            </Dialog.Panel>
                        </Transition.Child>
                    </div>
                </div>
            </Dialog>
        </Transition.Root>
    );
}

export default NewAreaModal;
