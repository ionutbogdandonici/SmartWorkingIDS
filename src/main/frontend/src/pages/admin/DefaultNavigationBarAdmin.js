import React from "react";
import { Outlet } from "react-router-dom";
import Dashboard from "../../components/Dashboard";

function DashboardAdmin() {
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
            <main>
                <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
                    <Outlet />
                </div>
            </main>
        </div>
    );
}

export default DashboardAdmin;
