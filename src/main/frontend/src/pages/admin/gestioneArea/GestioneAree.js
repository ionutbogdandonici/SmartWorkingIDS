import React, { useState, useEffect } from "react";
import axios from "axios";
import Button from "../../../components/Button";
import NewAreaModal from "./NewAreaModal";

function GestioneAree() {

    const [aree, setAree] = useState([]);
    const [show, setShow] = useState(false);

    useEffect(() => {
        axios.get("http://localhost:8080/aree/all").then((res) => {
            setAree(res.data);
            console.log(res.data);
        });
    }, []);

    const showModal = () => {
        setShow(true);
    } 

    return (
        <div className="bg-white rounded-lg">
            <div className="px-4 py-5 sm:px-6 rounded">
                <div className="-ml-4 -mt-2 flex items-center justify-between flex-wrap sm:flex-nowrap">
                    <div className="ml-4 mt-2">
                        <h3 className="text-lg leading-6 font-medium text-gray-900">Aree nel Sistema</h3>
                    </div>
                    <div className="ml-4 mt-2 flex-shrink-0">
                        <Button type="button" decoration="primary" text="Crea Nuova Area" onClick={showModal}/>
                    </div>
                </div>
            </div>
            <div className="flex flex-col">
                <div className="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                    <div className="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
                        <div className="shadow overflow-hidden border-t border-gray-200 sm:rounded-lg">
                            <table className="min-w-full divide-y divide-gray-200">
                                <thead className="bg-gray-50">
                                    <tr>
                                        <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                            Toponimo
                                        </th>
                                        <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                            Territorio
                                        </th>
                                        <th scope="col" className="relative px-6 py-3">
                                            <span className="sr-only">Edit</span>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {aree.map((area) => (
                                        <tr key={area.idArea} className="bg-white border-b">
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{area.toponimo}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{area.territorio.nome}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                                <button type="button" className="text-green-600 hover:text-green-900" >
                                                    Modifica
                                                </button>
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                            <NewAreaModal show={show} setShow={(bool) => setShow(bool)} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default GestioneAree;
