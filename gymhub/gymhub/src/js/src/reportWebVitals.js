const reportWebVitals = onPerfEntry => {
  if (onPerfEntry && onPerfEntry instanceof Function) {

    // Dynamically import the 'web-vitals' module
    import('web-vitals').then(({ getCLS, getFID, getFCP, getLCP, getTTFB }) => {
      
      // Use functions from the 'web-vitals' module
      getCLS(onPerfEntry);
      getFID(onPerfEntry);
      getFCP(onPerfEntry);
      getLCP(onPerfEntry);
      getTTFB(onPerfEntry);
    });
  }
};

export default reportWebVitals;
