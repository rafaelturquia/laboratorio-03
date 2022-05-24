import React, { useState, useEffect } from 'react'
import { Line, Button } from 'arwes'
import { HEADERS } from './crudes'

const ListaDeVantagens = (props) => {
    const [itens, setItens] = useState([])

    useEffect(() => fetch('vantagem/all')
        .then(res => res.json())
        .then(res => setItens(res)), [])

    return (
        <div>
            <h2>Vantagens cadastradas</h2>
            <Line animate layer='success' />
            {itens.length > 0 && itens.map((itemMapped, i) => <React.Fragment key={i}>
                <h3>{`Vantagem ${i + 1}`}</h3>
                <p>{`Valor:${itemMapped.valor}`}</p>
                <p>{`Descrição: ${itemMapped.descricao}`}</p>
                <p>{`Empresa: ${itemMapped.empresa.nome}`}</p>
                {props.user.papel === "aluno" &&
                    <Button onClick={() => fetch(`resgate/${props.user.id}/${itemMapped.id}`, {
                        method: 'POST',
                        headers: HEADERS,
                    }).then(res => res.text()).then(res => alert(res))}
                    >Solicitar</Button>}
                <Line animate layer='success' />
            </React.Fragment>)}
        </div>
    );
}

export default ListaDeVantagens
