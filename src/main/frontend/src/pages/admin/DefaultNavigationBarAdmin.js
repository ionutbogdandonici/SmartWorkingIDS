import React from "react";
import { Outlet } from "react-router-dom";
import Dashboard from "../../components/Dashboard";

function DefaultNavigationBarAdmin() {
    const navigationLinks = [
        { name: "Dashboard", href: "/admin/dashboard", current: false },
        { name: "Gestione Aree", href: "/admin/gestioneAree", current: false },
    ];

    const userNavigationLinks = [
        { name: "Dashboard", href: "/admin/dashboard" },
        { name: "Impostazioni", href: "/admin/impostazioni" },
        { name: "Logout", href: "/logout" },
    ];

    const user = {
        name: "Admin",
        email: "riccardo.ceccarani@studenti.unicam.it",
    };

    return (
        <div>
            <Dashboard navigationLinks={navigationLinks} userNavigationLinks={userNavigationLinks} user={user} />
            <header className="bg-white shadow-sm">
                <div className="max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8">
                    <h1 className="text-lg leading-6 font-semibold text-gray-900">Admin Workspace</h1>
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

export default DefaultNavigationBarAdmin;
