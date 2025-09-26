import Input from "../components/input";
import Navbar from "../components/navbar";

export default function Login() {
  return (
    <div className="bg-light min-vh-100 d-flex flex-column">
      {/* Navbar */}
      <Navbar />
      <div className="d-flex justify-content-center mt-5">
        <div className="card shadow p-5 w-100" style={{ maxWidth: "1000px" }}>
          <h2 className="text-center mb-5 fw-bold text-primary display-6">
            Login
          </h2>
          <form>
            {/* Username */}
            <Input
              label="Username"
              type="email"
              placeholder="Enter your username"
            />

            {/* Password */}
            <Input
              label="Password"
              type="password"
              placeholder="Enter your password"
            />

            {/* Button */}
            <button
              type="submit"
              className="btn btn-primary w-100 mt-4 py-3 fs-5">
              Login
            </button>

            {/* Extra Links */}
            <div className="text-center mt-4">
              <a href="#" className="text-decoration-none">
                Forgot Password?
              </a>
            </div>
            <div className="text-center mt-3">
              <span>Don’t have an account? </span>
              <a href="#" className="text-primary fw-bold">
                Sign Up
              </a>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
