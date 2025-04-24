import * as React from "react";
import {ChangeEvent, useState, FormEvent} from "react";
import {createInventoryItem} from "./FormService.tsx";

interface InventoryItem {
    item_description: string;
    qty: number| '';
    cost: number | '';
    date_of_last_inventory: string;
}

const InventoryForm: React.FC = () => {
    const [formData,setFormData] = useState<InventoryItem>(
        {item_description: '',
        qty: '',
        cost: '',
        date_of_last_inventory: ''}
    )

    function handleChange(e: ChangeEvent<HTMLInputElement>) {
        const{name, value} = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: name === 'qty' || name === 'cost' ? (value === '' ? '' : parseFloat(value)) : value
        }))
    }
    const handleSubmit =  async (e: FormEvent)=> {
        e.preventDefault();
        console.log(e);
        const formDataToSend = {
            item_description: formData.item_description,
            qty: formData.qty,
            cost: formData.cost,
            date_of_last_inventory: new Date(formData.date_of_last_inventory).toISOString().split("T")[0]

        };
        console.log(formDataToSend)
        try{
            const savedItem = await createInventoryItem(formDataToSend);
            console.log('Item saved:',savedItem)

            setFormData({
                item_description: '',
                qty: '',
                cost: '',
                date_of_last_inventory: ''
            });
        }catch (error){
            console.error('Error saving item:',error)
        }
    }

    return (
        <form onSubmit={handleSubmit} className="max-w-xl mx-auto bg-white p-6 rounded-2xl shadow-md space-y-6">
            <h2 className="text-2xl font-bold text-gray-800">Add Inventory Item</h2>


            <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Item Description</label>
                <input type="text" name="item_description"
                       value={formData.item_description}
                       onChange={handleChange}
                       className="w-full px-4 py-2 border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                       placeholder="e.g. Widget A"/>
            </div>


            <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Quantity</label>
                <input type="number" name="qty"
                       value={formData.qty}
                       onChange={handleChange}
                       className="w-full px-4 py-2 border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                       placeholder="e.g. 100"/>
            </div>


            <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Cost</label>
                <input type="number" step="0.01" name="cost"
                       value={formData.cost}
                       onChange={handleChange}
                       className="w-full px-4 py-2 border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                       placeholder="e.g. 49.99"/>
            </div>


            <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Date of Last Inventory</label>
                <input type="date" name="date_of_last_inventory"
                       value={formData.date_of_last_inventory}
                       onChange={handleChange}
                       className="w-full px-4 py-2 border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"/>
            </div>


            <button type="submit"
                    className="w-full bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition">
                Save Item
            </button>
        </form>
    )
}
export default InventoryForm