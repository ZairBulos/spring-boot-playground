import { useAuth0 } from "@auth0/auth0-react";

const RegisterButton = () => {
  const { loginWithRedirect } = useAuth0();

  const handleRegister = () => {
    loginWithRedirect({
      appState: { returnTo: "/" },
      authorizationParams: { screen_hint: "signup" },
    });
  };

  return (
    <button
      type="button"
      onClick={handleRegister}
      className="px-4 py-2 rounded bg-black text-white font-semibold hover:bg-neutral-900 hover:cursor-pointer transition-colors"
    >
      Sign Up
    </button>
  );
};

export default RegisterButton;
