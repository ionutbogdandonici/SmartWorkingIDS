import React, { useState, Fragment } from "react";
import { CheckIcon, SelectorIcon } from "@heroicons/react/solid";
import { Field, ErrorMessage } from "formik";

import { Listbox, Transition } from "@headlessui/react";

function classNames(...classes) {
    return classes.filter(Boolean).join(" ");
}

function SelectFieldArea({ name, label, values }) {
    const [selected, setSelected] = useState(values[0]);

    return (
        <Field name={name} id={name}>
            {({ field, form: { touched, errors }, meta }) => (
                <Listbox value={selected} onChange={setSelected}>
                    {({ open }) => (
                        <>
                            <Listbox.Label className="block text-sm font-medium text-gray-700">Territorio</Listbox.Label>
                            <div className="mt-1 relative">
                                <Listbox.Button className="relative w-full bg-white border border-gray-300 rounded-md shadow-sm pl-3 pr-10 py-2 text-left cursor-default focus:outline-none focus:ring-1 focus:ring-green-500 focus:border-green-500 sm:text-sm">
                                    <span className="w-full inline-flex truncate">
                                        <span className="truncate">{selected.nome}</span>
                                        <span className="ml-2 truncate text-gray-500">{selected.regione.nome}</span>
                                    </span>
                                    <span className="absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none">
                                        <SelectorIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
                                    </span>
                                </Listbox.Button>

                                <Transition show={open} as={Fragment} leave="transition ease-in duration-100" leaveFrom="opacity-100" leaveTo="opacity-0">
                                    <Listbox.Options className="absolute z-10 mt-1 w-full bg-white shadow-lg max-h-60 rounded-md py-1 text-base ring-1 ring-black ring-opacity-5 overflow-auto focus:outline-none sm:text-sm">
                                        {values.map((territorio) => (
                                            <Listbox.Option key={territorio.idTerritorio} className={({ active }) => classNames(active ? "text-white bg-green-600" : "text-gray-900", "cursor-default select-none relative py-2 pl-3 pr-9")} value={territorio}>
                                                {({ selected, active }) => (
                                                    <>
                                                        <div className="flex">
                                                            <span className={classNames(selected ? "font-semibold" : "font-normal", "truncate")}>{territorio.nome}</span>
                                                            <span className={classNames(active ? "text-green-200" : "text-gray-500", "ml-2 truncate")}>{territorio.regione.nome}</span>
                                                        </div>

                                                        {selected ? (
                                                            <span className={classNames(active ? "text-white" : "text-green-600", "absolute inset-y-0 right-0 flex items-center pr-4")}>
                                                                <CheckIcon className="h-5 w-5" aria-hidden="true" />
                                                            </span>
                                                        ) : null}
                                                    </>
                                                )}
                                            </Listbox.Option>
                                        ))}
                                    </Listbox.Options>
                                </Transition>
                                <ErrorMessage name={name} component="div" className="text-red-600 font-semibold text-sm mt-2" />
                            </div>
                        </>
                    )}
                </Listbox>
            )}
        </Field>
    );
}

export default SelectFieldArea;
