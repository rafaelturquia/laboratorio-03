import { useEffect, useState } from "react";

const Extrato = (props) => {
    const [extrato, setExtrato] = useState([])

    useEffect(() => fetch(`${props.user.papel}/extrato/${props.user.id}`)
        .then(res => res.json())
        .then(res => setExtrato(res)), [])

    return (<div>
        <h1>Extrato</h1>
        {extrato?.map(transf => (<p>{`Valor: ${transf.valor} - Motivo: ${transf.motivo} - Aluno: ${transf.aluno.nome} - Professor: ${transf.professor.nome}`}</p>))}
        <p>{`Saldo: ${props.user.saldo}`}</p>
    </div>)
}

export default Extrato