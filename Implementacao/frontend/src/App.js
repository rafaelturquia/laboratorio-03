import { useState } from 'react'
import Crud from './crudes'
import { ThemeProvider, createTheme, Arwes, Puffs } from 'arwes';

const styling = {
  cursor: 'pointer',
  color: 'blue',
  textDecoration: 'underline'
}

const App = () => {
  const [page, setPage] = useState("nada")

  return (
    <ThemeProvider theme={createTheme()}>
      <Arwes>
        <Puffs>
          <div className="App">
            <div>
              <div style={styling} onClick={() => setPage("aluno")}>Aluno</div>
              <div style={styling} onClick={() => setPage("empresa")}>Empresa Parceira</div>
            </div>
            {page !== "nada" && <Crud oQue={page} />}
          </div>
        </Puffs>
      </Arwes>
    </ThemeProvider>
  );
}

export default App
