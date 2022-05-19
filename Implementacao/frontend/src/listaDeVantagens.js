import React, { useState, useEffect } from 'react'
import { Line } from 'arwes'

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
                <Line animate layer='success' />
            </React.Fragment>)}
        </div>
    );
}

export default ListaDeVantagens
