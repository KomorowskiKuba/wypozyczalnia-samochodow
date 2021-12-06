import React, { useEffect, useState } from 'react';
import './App.css';
import GetList from './components/GetList';

function App() {

  const [allCarsList, setAllCarsList] = useState([]);
  useEffect(() => {
    async function fetchAllCarsList() {
      try {
        const requestUrl = 'http://localhost:8080/carsapi/cars';
        const response = await fetch(requestUrl);
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
