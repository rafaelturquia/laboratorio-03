import { useState } from 'react'
import Crud from './crudes'

const styling = {
  cursor: 'pointer',
  color: 'blue',
  textDecoration: 'underline'
}

const App = () => {
  const [page, setPage] = useState("nada")

  return (
    <div className="App">
      <div>
        <div style={styling} onClick={() => setPage("aluno")}>Aluno</div>
        <div style={styling} onClick={() => setPage("empresa")}>Empresa Parceira</div>
      </div>
      {page !== "nada" && <Crud oQue={page} />}
    </div>
  );
}

export default App
