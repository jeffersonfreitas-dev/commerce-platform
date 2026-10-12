import { CartItem } from './cart.model';

export type OrderStatus =
  | 'PENDING' | 'PAID' | 'SHIPPED' | 'DELIVERED' | 'CANCELED';

export interface Address {
  street: string;
  number: string;
  complement?: string;
  city: string;
  state: string;
  zip: string;
}

export interface Order {
  id: string;
  items: CartItem[];
  total: number;
  status: OrderStatus;
  createdAt: string;
  address: Address;
  paymentMethod: 'PIX' | 'CREDIT_CARD' | 'BOLETO';
}