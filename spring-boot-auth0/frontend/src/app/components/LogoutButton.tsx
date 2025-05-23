import { useAuth0 } from "@auth0/auth0-react";

const LogoutButton = () => {
  const { logout, user } = useAuth0();

  const handleLogOut = () => {
    logout({ logoutParams: { returnTo: window.location.origin } });
  };

  return (
    user && (
      <button
        type="button"
        onClick={handleLogOut}
        className="px-4 py-2 rounded bg-red-500 text-white font-semibold hover:cursor-pointer hover:bg-red-600 transition-colors"
      >
        Log Out
      </button>
    )
  );
};

export default LogoutButton;
