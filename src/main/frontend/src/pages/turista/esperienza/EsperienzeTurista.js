import React, {useState, useEffect} from "react";
import Button from "../../../components/Button";
import PrenotazioneEsperienzaModal from "./PrenotazioneEsperienzaModal";
import axios from "axios";

function EsperienzeTurista() {

    const [show, setShow] = useState(false);

    const [esperienze, setEsperienze] = useState([]);

    const headers = {
        headers: {
            Authorization : `Bearer ${sessionStorage.getItem("Access-Token")}`,
        },
    };

    useEffect(() => {
        axios.get("http://localhost:8080/esperienze/all", headers).then((res) => {
            console.log(res.data);
            setEsperienze(res.data);
        })
    }, [show]);

    const showModal = () => {
        setShow(true);
    }

    return (
        <div className="bg-white rounded-lg">
            <div className="px-4 py-5 sm:px-6 rounded">
                <div className="-ml-4 -mt-2 flex items-center justify-between flex-wrap sm:flex-nowrap">
                    <div className="ml-4 mt-2">
                        <h3 className="text-lg leading-6 font-medium text-gray-900">Esperienze Disponibili</h3>
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
                                            Nome
                                        </th>
                                        <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                            Descrizione
                                        </th>
                                        <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                            Data
                                        </th>
                                        <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                                            Posti Disponibili
                                        </th>
                                        <th scope="col" className="relative px-6 py-3">
                                            <span className="sr-only">Prenota</span>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {esperienze.map((esperienza) => (
                                        
                                        <tr key={esperienza.id} className="bg-white border-b">
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{esperienza.nome}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-500">{esperienza.descrizione}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-500">{esperienza.data.slice(0,10)}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-500">{esperienza.postiMax-esperienza.postiRiservati}</td>
                                            <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                                <Button type="button" decoration="primary" text="Prenota" onClick={showModal}/>
                                            </td>
                                            <PrenotazioneEsperienzaModal show={show} setShow={(bool) => setShow(bool)} esperienza={esperienza} />
                                        </tr>                                        
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default EsperienzeTurista;
