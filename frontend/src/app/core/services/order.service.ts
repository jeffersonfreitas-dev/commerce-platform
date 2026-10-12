import { Injectable, signal } from '@angular/core';
import { Order, Address } from '../models/order.model';
import { CartItem } from '../models/cart.model';

@Injectable({ providedIn: 'root' })
export class OrderService {
  private _orders = signal<Order[]>([]);
  readonly orders = this._orders.asReadonly();

  constructor() {
    const raw = localStorage.getItem('ecom_orders');
    if (raw) { try { this._orders.set(JSON.parse(raw)); } catch {} }
  }

  create(items: CartItem[], total: number, address: Address, paymentMethod: Order['paymentMethod']): Order {
    const order: Order = {
      id: 'ORD-' + Date.now().toString(36).toUpperCase(),
      items: JSON.parse(JSON.stringify(items)),
      total,
      status: 'PAID',
      createdAt: new Date().toISOString(),
      address,
      paymentMethod,
    };
    const next = [order, ...this._orders()];
    this._orders.set(next);
    localStorage.setItem('ecom_orders', JSON.stringify(next));
    return order;
  }
}