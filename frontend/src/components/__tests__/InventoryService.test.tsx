import {afterAll, afterEach, beforeAll, describe, expect, it} from "vitest";
import axios from "axios";
import {setupServer} from "msw/node"
import {InventoryItem} from "../InventoryType.ts";
import {http, HttpResponse} from "msw";
import {createInventoryItem} from "../FormService.tsx";

describe('InventoryService', () => {

    axios.defaults.baseURL = "http://localhost:8080"

    const server = setupServer()
    beforeAll(()=> server.listen({onUnhandledRequest: 'error'}))
    afterAll(() => server.close())
    afterEach(() => server.resetHandlers())

    it('should send a post request with a new inventory item', async () => {
        const expected: InventoryItem = {
            id: 1,
            item_description: 'Chin is dead',
            qty: 1,
            cost: 6.99,
            date_of_last_inventory: '2025-04-04'
        }
        server.use(http.post('/api/inventory',()=>
        HttpResponse.json(expected,{status: 201})
        ))

        const result = await createInventoryItem(expected);
        expect(result).toEqual(expected);
    });

});