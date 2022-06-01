import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import DashboardAdmin from "./pages/admin/DashboardAdmin";
import DefaultNavigationBarAdmin from "./pages/admin/DefaultNavigationBarAdmin";
import GestioneAree from "./pages/admin/GestioneAree";

function App() {
    return (
        <div className="font-inter h-full">
            <Router>
                <Routes>
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
