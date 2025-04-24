import axios from "axios";

const API_URL = 'http://localhost:8080/api/inventory';

export interface InventoryItem {
    item_description: string;
    qty: number;
    cost: number;
    date_of_last_inventory: string;
}

export const createInventoryItem = async (item: {
    item_description: string;
    qty: number | "";
    cost: number | "";
    date_of_last_inventory: string
}) => {
    const response = await axios.post(API_URL,item);
    return response.data;
};

