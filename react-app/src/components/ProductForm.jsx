import { useEffect, useState } from "react"

const initialDataForm = {
    id: 0,
    name: '',
    description: '',
    price: ''
}
export const ProductForm = ({ handlerAdd, productSelected }) => {

    const [product, setProduct] = useState(initialDataForm)

    const { id, name, description, price } = product

    useEffect(() => {
        setProduct(productSelected)
    }, [productSelected])

    return (
        <form onSubmit={(event) => {
            event.preventDefault()

            if (!name || !description || !price) {
                alert('Debe completar los datos del formulario')
                return
            }

            // console.log(product)
            handlerAdd(product)
            setProduct(initialDataForm)
        }}>
            <div>
                <input placeholder="Name"
                    className="form-control my-3 w-75"
                    name="name"
                    value={name}
                    onChange={(event) => setProduct({
                        ...product,
                        name: event.target.value
                    })}
                />
            </div>

            <div>
                <input placeholder="Description"
                    className="form-control my-3 w-75"
                    name="description"
                    value={description}
                    onChange={(event) => setProduct({
                        ...product,
                        description: event.target.value
                    })}
                />
            </div>

            <div>
                <input placeholder="Price"
                    className="form-control my-3 w-75"
                    name="price"
                    value={price}
                    onChange={(event) => setProduct({
                        ...product,
                        price: event.target.value
                    })}
                />
            </div>

            <button type="submit" className="btn btn-primary">
                {id > 0 ? 'Update' : 'Create'}
            </button>
        </form>
    )
}