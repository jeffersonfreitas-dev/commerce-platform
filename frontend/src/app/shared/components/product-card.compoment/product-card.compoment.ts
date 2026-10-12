import { Component, inject, Input } from '@angular/core';
import { Product } from '../../../core/models/product.model';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { CartService } from '../../../core/services/cart.service';
import { ToastService } from '../../../core/services/toast.service';

@Component({
  standalone: true,
  imports: [CommonModule, RouterLink, CurrencyPipe],
  selector: 'app-product-card',
  styleUrl: './product-card.compoment.scss',
  templateUrl: './product-card.compoment.html',
})
export class ProductCardCompoment {
  @Input({ required: true }) product!: Product;
  private cart = inject(CartService);
  private toast = inject(ToastService);

  get discountPct() {
    if (!this.product.originalPrice) return 0;
    return Math.round((1 - this.product.price / this.product.originalPrice) * 100);
  }

  add() {
    this.cart.add(this.product);
    this.toast.show(`"${this.product.name}" adicionado ao carrinho`, 'success');
  }  
}
