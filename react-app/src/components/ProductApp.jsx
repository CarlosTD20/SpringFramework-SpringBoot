import { useEffect, useState } from "react"
import { create, findAll, listProducts, remove, update } from "../services/ProductService"
import { ProductGrid } from "./ProductGrid"
import { PropTypes } from "prop-types"
import { ProductForm } from "./ProductForm"

export const ProductApp = ({ title }) => {

    const [products, setProducts] = useState([])

    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    })

    const getProducts = async () => {
        const result = await findAll()
        console.log(result)
        setProducts(result.data._embedded.products)
    }

    useEffect(() => {
        // const result = listProducts()
        getProducts()
    }, [])

    const handlerProduct = async (product) => {
        // console.log(product)

        if (product.id > 0) {
            const result = await update(product)
            setProducts(products.map(prod => {
                if (prod.id == product.id) {
                    return { ...result.data }
                }
                return prod
            }))
        } else {
            const result = await create(product)
            setProducts([...products, { ...result.data }])
        }
    }

    const handlerRemove = (id) => {
        console.log(id)
        remove(id)
        setProducts(products.filter(prod => prod.id != id))
    }

    const handlerProductSelected = (product) => {
        setProductSelected({ ...product })
    }

    return (
        <divc lassName="container my-4">
            <h2>{title}</h2>
            <div className="row">
                <div className="col">
                    <ProductForm handlerAdd={handlerProduct} productSelected={productSelected} />
                </div>
                <div className="col">
                    {
                        products.length > 0 ? <ProductGrid products={products} handlerRemove={handlerRemove} handlerProductSelected={handlerProductSelected} /> : <div className="alert alert-warning">No hay productos en el sistema!!!</div>
                    }

                </div>
            </div>
        </divc>
    )
}

ProductApp.propTypes = {
    title: PropTypes.string.isRequired
}