import LoginButton from "../components/LoginButton";
import LogoutButton from "../components/LogoutButton";
import RegisterButton from "../components/RegisterButton";

const Header = () => {
  return (
    <header className="flex items-center justify-between px-6 py-4 shadow">
      <a href="https://auth0.com" target="_blank">
        <h1 className="text-2xl font-bold hover:underline">AUTH0</h1>
      </a>
      <nav className="flex gap-4">
        <LoginButton />
        <RegisterButton />
        <LogoutButton />
      </nav>
    </header>
  );
};

export default Header;
