export const formatDate = (date, config) => {
  
    // Define default date formatting options
    const defaultOptions = { day: "numeric", month: "long", year: "numeric" };

    // Use custom options if provided, otherwise, use the default options
    const options = config ? config : defaultOptions;

    // Format the date using the provided or default options
    return new Date(date).toLocaleDateString("en-US", options);
}

