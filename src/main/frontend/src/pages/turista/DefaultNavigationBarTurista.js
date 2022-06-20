import axios from "axios";
import React, { useEffect, useState } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import Dashboard from "../../components/Dashboard";

function HomePage() {
    const navigator = useNavigate();
    const [user, setUser] = useState({});

    axios.defaults.headers.common["Authorization"] = `Bearer ${sessionStorage.getItem("Access-Token")}`;

    useEffect(() => {
        if (!sessionStorage.getItem("Access-Token")) {
            navigator("/");
        }
        const username = sessionStorage.getItem("Username");

        const bodyParameters = {
            username: username,
        }

        if (username)
            axios
                .get("http://localhost:8080/turisti/getByUsername", {params: bodyParameters})
                .then((res) => {
                    setUser(res.data);
                });
    }, []);

    const navigationLinks = [
        { name: "Home", href: "/turista/home", current: false },
        { name: "Esperienze", href: "/turista/esperienze", current: false },
    ];

    const userNavigationLinks = [
        { name: "Home", href: "/turista/home" },
        { name: "Esperienze", href: "/turista/esperienze" },
        { name: "Logout", href: "/logout" },
    ];

    return (
        <div>
            <Dashboard navigationLinks={navigationLinks} userNavigationLinks={userNavigationLinks} user={user} />
            <header className="bg-white shadow-sm">
                <div className="max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8">
                    <h1 className="text-lg leading-6 font-semibold text-gray-900">Turista Workspace</h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
                    <Outlet />
                </div>
            </main>
        </div>
    );
}

export default HomePage;
