import { Injectable, signal } from '@angular/core';

export interface Toast { id: number; text: string; type: 'info'|'success'|'error' }

@Injectable({ providedIn: 'root' })
export class ToastService {
  private _items = signal<Toast[]>([]);
  readonly items = this._items.asReadonly();

  show(text: string, type: Toast['type'] = 'info', ms = 3000) {
    const id = Date.now() + Math.random();
    this._items.update(l => [...l, { id, text, type }]);
    setTimeout(() => this._items.update(l => l.filter(t => t.id !== id)), ms);
  }
}