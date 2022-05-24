import { useEffect, useState } from "react";

const Extrato = (props) => {
    const [extrato, setExtrato] = useState([])
    const [resgates, setResgates] = useState([])

    useEffect(() => {
        fetch(`${props.user.papel}/extrato/${props.user.id}`)
            .then(res => res.json())
            .then(res => setExtrato(res))
        if (props.user.papel === "aluno") {
            fetch(`resgate/aluno/${props.user.id}`)
                .then(res => res.json())
                .then(res => setResgates(res))
            fetch(`aluno/${props.user.id}`)
                .then(res => res.json())
                .then(res => props.setUser({ ...props.user, saldo: res.conta.saldo })
                )
        }
    }, [])

    return (<div>
        <h1>Extrato</h1>
        <h2>Transferência</h2>
        {extrato ?.map((transf, i) => (<p key={i}>{`${i + 1} - Valor: ${transf.valor} - Motivo: ${transf.motivo} - Aluno: ${transf.aluno.nome} - Professor: ${transf.professor.nome}`}</p>))}
        <p>{`Saldo: ${props.user.saldo}`}</p>
        {props.user.papel === "aluno" && <>
            <h2>Resgates</h2>
            {resgates ?.map((resg, i) => <p>{`${i + 1} - Valor: ${resg.vantagem.valor} - Descricao: ${resg.vantagem.descricao} - Empresa: ${resg.empresa.nome}`}</p>)}
        </>}
    </div>)
}

export default Extrato