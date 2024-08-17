import { ProductType } from "./product-type";

export class Product {
  id: number;
  name: string;
  description: string;
  price: number;
  quantity: number;
  type: ProductType;
}
