import React, { useEffect, useState } from 'react';
import './App.css';
import GetList from './components/GetList';

app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  next();
});

function App() {
  

  const [allCarsList, setAllCarsList] = useState([]);
  useEffect(() => {
    async function fetchAllCarsList() {
      try {
        const requestUrl = 'http://localhost:8080/carsapi/cars';
        const response = await fetch(requestUrl, {credentials: 'same-origin'});
        const responseJSON = await response.json();
        console.log(responseJSON);
        setAllCarsList(responseJSON);
      } catch {

      }
    }
    fetchAllCarsList();
  }, []);

  return (
      <GetList allCarsList={allCarsList}/>    
  );
}

export default App;
