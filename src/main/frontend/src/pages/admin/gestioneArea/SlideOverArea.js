import { Fragment, useState, useEffect } from "react";
import { Dialog, Transition } from "@headlessui/react";
import { XIcon } from "@heroicons/react/outline";
import axios from "axios";

export default function SlideOverArea({ area }) {
    
    // TODO - Ordinare gli elementi nell'array
    const [terrtitori, setTerritori] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/territori/all").then((res) => {
            setTerritori(res.data);
        });
    }, []);

    const [open, setOpen] = useState(true);

    return (
        <Transition.Root show={open} as={Fragment}>
            <Dialog as="div" className="fixed inset-0 overflow-hidden" onClose={setOpen}>
                <div className="absolute inset-0 overflow-hidden">
                    <Dialog.Overlay className="absolute inset-0">
                        <div className="fixed inset-y-0 pl-16 max-w-full right-0 flex">
                            <Transition.Child as={Fragment} enter="transform transition ease-in-out duration-500 sm:duration-700" enterFrom="translate-x-full" enterTo="translate-x-0" leave="transform transition ease-in-out duration-500 sm:duration-700" leaveFrom="translate-x-0" leaveTo="translate-x-full">
                                <div className="w-screen max-w-md">
                                    <form className="h-full divide-y divide-gray-200 flex flex-col bg-white shadow-xl">
                                        <div className="flex-1 h-0 overflow-y-auto">
                                            <div className="py-6 px-4 bg-green-700 sm:px-6">
                                                <div className="flex items-center justify-between">
                                                    <Dialog.Title className="text-lg font-medium text-white">
                                                        {
                                                            // TODO - use the area name
                                                        }
                                                        Modifica Area
                                                    </Dialog.Title>
                                                    <div className="ml-3 h-7 flex items-center">
                                                        <button type="button" className="bg-green-700 rounded-md text-green-200 hover:text-white focus:outline-none focus:ring-2 focus:ring-white" onClick={() => setOpen(false)}>
                                                            <span className="sr-only">Close panel</span>
                                                            <XIcon className="h-6 w-6" aria-hidden="true" />
                                                        </button>
                                                    </div>
                                                </div>
                                                <div className="mt-1">
                                                    <p className="text-sm text-green-300">Modifica l'informazione all'interno dei campi per aggiornare i dati dell'area</p>
                                                </div>
                                            </div>
                                            <div className="flex-1 flex flex-col justify-between">
                                                <div className="px-4 divide-y divide-gray-200 sm:px-6">
                                                    <div className="space-y-6 pt-6 pb-5">
                                                        <div>
                                                            <label htmlFor="area-toponimo" className="block text-sm  font-medium text-gray-900">
                                                                Toponimo
                                                            </label>
                                                            <div className="mt-1">
                                                                <input type="text" name="area-toponimo" id="area-toponimo" className="block w-full px-4 py-2 shadow-sm sm:text-sm focus:ring-green-500 focus:border-green-500 border border-gray-300 rounded-md" />
                                                            </div>
                                                        </div>

                                                        <div>
                                                            <label htmlFor="area-territorio" className="block text-sm font-medium text-gray-900">
                                                                Territorio
                                                            </label>
                                                            <select id="area-territorio" name="area-territorio" className="mt-1 block w-full pl-3 pr-10 py-2 text-base shadow-sm border border-gray-300 focus:outline-none focus:ring-green-500 focus:border-green-500 sm:text-sm rounded-md" defaultValue="Canada">
                                                                {terrtitori.map((territorio) => (
                                                                    <option key={territorio.id} value={territorio.id}>{territorio.nome} - {territorio.regione.nome}</option>
                                                                ))}
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="felx-shrink-0 px-4 py-4 flex justify-end">
                                            <button type="button" className="bg-white py-2 px-4 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500" onClick={() => setOpen(false)}>
                                                Cancel
                                            </button>
                                            <button type="submit" className="ml-4 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500">
                                                Save
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </Transition.Child>
                        </div>
                    </Dialog.Overlay>
                </div>
            </Dialog>
        </Transition.Root>
    );
}
