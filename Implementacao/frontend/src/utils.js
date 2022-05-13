export const Inputs = ({ arrayProps, estado, setEstado }) => {
    return (<>{arrayProps.map((prop, i) =>
        <input key={i} placeholder={prop} type="text" value={estado[prop]}
            onChange={e => {
                let itemAtualizado = { ...estado }
                itemAtualizado[prop] = e.target.value
                setEstado(itemAtualizado)
            }} />)}
    </>)
}