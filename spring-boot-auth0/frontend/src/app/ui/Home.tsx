import { useAuth0 } from "@auth0/auth0-react";
import { api } from "../../shared/utils/axios-instance";
import { useState } from "react";

const Home = () => {
  const { getAccessTokenSilently, isAuthenticated } = useAuth0();
  const [message, setMessage] = useState<string>("");

  const handlePublicCall = async () => {
    const response = await api.get("/public");
    setMessage(JSON.stringify(response.data));
  };

  const handleProtectedCall = async () => {
    if (!isAuthenticated) {
      setMessage("You need to log in");
      return;
    }

    const token = await getAccessToken();
    const response = await api.get("/protected", {
      headers: { Authorization: `Bearer ${token}` },
    });
    setMessage(JSON.stringify(response.data));
  };

  const handleAdminCall = async () => {
    if (!isAuthenticated) {
      setMessage("You need to log in");
      return;
    }

    const token = await getAccessToken();
    const response = await api.get("/admin", {
      headers: { Authorization: `Bearer ${token}` },
    });
    setMessage(JSON.stringify(response.data));
  };

  const getAccessToken = async () => {
    return await getAccessTokenSilently({
      authorizationParams: {
        audience: import.meta.env.VITE_AUTH0_AUDIENCE,
      },
    });
  };

  return (
    <main className="py-12">
      <section className="flex flex-row items-center justify-center gap-8 mb-8">
        <button
          type="button"
          onClick={handlePublicCall}
          className="px-4 py-2 rounded border bg-white hover:bg-green-100 hover:cursor-pointer transition-colors"
        >
          Pulic Endpoint
        </button>

        <button
          type="button"
          onClick={handleProtectedCall}
          className="px-4 py-2 rounded border bg-white hover:bg-blue-100 hover:cursor-pointer transition-colors"
        >
          Protected Endpoint
        </button>

        <button
          type="button"
          onClick={handleAdminCall}
          className="px-4 py-2 rounded border bg-white hover:bg-red-100 hover:cursor-pointer transition-colors"
        >
          Admin Endpoint
        </button>
      </section>

      <section className="flex items-center justify-center">
        <h2>{message}</h2>
      </section>
    </main>
  );
};

export default Home;
