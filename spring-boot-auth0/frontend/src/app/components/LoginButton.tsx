import { useAuth0 } from "@auth0/auth0-react";

const LoginButton = () => {
  const { loginWithRedirect } = useAuth0();

  const handleLogIn = () => {
    loginWithRedirect({ appState: { returnTo: window.location.pathname } });
  };

  return (
    <button
      type="button"
      onClick={handleLogIn}
      className="px-4 py-2 rounded bg-white text-black font-semibold border hover:bg-gray-100 transition-colors hover:cursor-pointer"
    >
      Log In
    </button>
  );
};

export default LoginButton;
