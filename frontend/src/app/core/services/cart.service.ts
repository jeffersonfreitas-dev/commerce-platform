import { Injectable, signal, computed, effect } from '@angular/core';
import { CartItem } from '../models/cart.model';
import { Product } from '../models/product.model';

@Injectable({ providedIn: 'root' })
export class CartService {
  private _items = signal<CartItem[]>([]);
  readonly items = this._items.asReadonly();

  readonly count = computed(() =>
    this._items().reduce((s, i) => s + i.quantity, 0)
  );
  readonly subtotal = computed(() =>
    this._items().reduce((s, i) => s + i.product.price * i.quantity, 0)
  );
  readonly discount = computed(() =>
    this._items().reduce((s, i) => {
      const orig = i.product.originalPrice;
      return s + (orig ? (orig - i.product.price) * i.quantity : 0);
    }, 0)
  );
  readonly shipping = computed(() =>
    this.subtotal() === 0 ? 0 : this.subtotal() >= 299 ? 0 : 24.9
  );
  readonly total = computed(() =>
    this.subtotal() + this.shipping()
  );

  constructor() {
    const raw = localStorage.getItem('ecom_cart');
    if (raw) { try { this._items.set(JSON.parse(raw)); } catch {} }
    effect(() => localStorage.setItem('ecom_cart', JSON.stringify(this._items())));
  }

  add(product: Product, qty = 1) {
    const items = [...this._items()];
    const idx = items.findIndex(i => i.product.id === product.id);
    if (idx >= 0) items[idx] = { ...items[idx], quantity: items[idx].quantity + qty };
    else items.push({ product, quantity: qty });
    this._items.set(items);
  }

  updateQty(productId: string, qty: number) {
    if (qty <= 0) return this.remove(productId);
    this._items.set(
      this._items().map(i => i.product.id === productId ? { ...i, quantity: qty } : i)
    );
  }

  remove(productId: string) {
    this._items.set(this._items().filter(i => i.product.id !== productId));
  }

  clear() { this._items.set([]); }
}