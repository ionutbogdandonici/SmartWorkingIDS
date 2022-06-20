import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import DashboardAdmin from "./pages/admin/DashboardAdmin";

import DefaultNavigationBarAdmin from "./pages/admin/DefaultNavigationBarAdmin";
import DefaultNavigationBarTurista from "./pages/turista/DefaultNavigationBarTurista";

import GestioneAree from "./pages/admin/gestioneArea/GestioneAree";
import HomeTurista from "./pages/turista/HomeTurista";
import EsperienzeTurista from "./pages/turista/esperienza/EsperienzeTurista";
import Login from "./pages/Login";
import RegistrazioneTurista from "./pages/RegistrazioneTurista";

function App() {
    return (
        <div className="h-full">
            <Router>
                <Routes>
                    <Route path="/" element={<Login />}/>
                    <Route path="/registrazioneTurista" element={<RegistrazioneTurista />}/>
                    <Route path="/turista/" element={<DefaultNavigationBarTurista />}>
                        <Route path="home" element={<HomeTurista />} />
                        <Route path="esperienze" element={<EsperienzeTurista />} />
                    </Route>
                    <Route path="/admin/" element={<DefaultNavigationBarAdmin />}>
                        <Route path="dashboard" element={<DashboardAdmin />} />
                        <Route path="gestioneAree" element={<GestioneAree />} />
                    </Route>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
