import { useState } from 'react'
import Crud from './crudes'
import Login from './login'
import { ThemeProvider, createTheme, Arwes, Puffs } from 'arwes';

const styling = {
  cursor: 'pointer',
  color: '#26dafd',
  textDecoration: 'underline'
}

const userDefault = {
  papel: "nenhum",
  isLogged: false,
  id: null,
  login: null,
}

const App = () => {
  const [page, setPage] = useState("nada")
  const [user, setUser] = useState(userDefault)
  console.log('login', page)

  return (
    <ThemeProvider theme={createTheme()}>
      <Arwes>
        <Puffs>
          <div className="App">
            {!user.isLogged && <>
              <div className="App2">
                <div style={styling} onClick={() => setPage("aluno")}>Cadastrar Aluno</div>
                <div style={styling} onClick={() => setPage("empresa")}>Cadastrar Empresa Parceira</div>
                <div style={styling} onClick={() => setPage("login")}>Login</div>
              </div>
              {(page === "aluno" || page === "empresa") && <Crud oQue={page} />}
              {page === "login" && <Login setUser={setUser}/>}
            </>}
            {user.isLogged && <>
              <div className="App2">
              <div style={styling} onClick={() => setPage("extrato")}>Verificar Extrato</div>
              <div style={styling} onClick={() => setPage("transferencia")}>Realizar transferência</div>
              <div style={styling} onClick={() => console.log('logout')}>Logout</div>
            </div></>
            }
          </div>
        </Puffs>
      </Arwes>
    </ThemeProvider>
  );
}

export default App
