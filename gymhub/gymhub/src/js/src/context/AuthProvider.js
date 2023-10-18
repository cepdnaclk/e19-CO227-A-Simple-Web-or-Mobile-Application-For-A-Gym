import { createContext, useState, useContext } from "react";

// Create an AuthContext
const AuthContext = createContext({});

// Define an AuthProvider component
export const AuthProvider = ({ children }) => {

    // State to manage authentication information
    const [auth, setAuth] = useState({});

    return (

        // Provide the auth context value to children
        <AuthContext.Provider value={{ auth, setAuth }}>
            {children}
        </AuthContext.Provider>
    )
}

// Custom hook to access the AuthContext
export const useAuth = () => {
    const context = useContext(AuthContext);

    // Ensure the hook is used within an AuthProvider
    if (!context) {
      throw new Error('useAuth must be used within an AuthProvider');
    }

    return context;
};

export default AuthContext;
