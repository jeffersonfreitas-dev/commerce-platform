import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CartService } from '../../core/services/cart.service';
import { OrderService } from '../../core/services/order.service';
import { ToastService } from '../../core/services/toast.service';
import { Address } from '../../core/models/order.model';

@Component({
  standalone: true,
  imports: [CommonModule, CurrencyPipe, FormsModule, RouterLink],
  selector: 'app-checkout',
  styleUrl: './checkout.scss',
  templateUrl: './checkout.html',
})
export class CheckoutComponent {
cart = inject(CartService);
  private orders = inject(OrderService);
  private toast = inject(ToastService);
  private router = inject(Router);

  step = signal<string>('address');
  processing = signal(false);

  address: Address = { street: '', number: '', complement: '', city: '', state: '', zip: '' };
  paymentMethod: 'PIX' | 'CREDIT_CARD' | 'BOLETO' = 'PIX';

  payOptions = [
    { value: 'PIX',         icon: '⚡', title: 'PIX',            desc: 'Aprovação imediata' },
    { value: 'CREDIT_CARD', icon: '💳', title: 'Cartão de crédito', desc: 'Até 12x sem juros' },
    { value: 'BOLETO',      icon: '🧾', title: 'Boleto',          desc: 'Vencimento em 3 dias úteis' },
  ] as const;

  goPayment() {
    if (!this.address.zip || !this.address.city || !this.address.street || !this.address.number || !this.address.state) {
      this.toast.show('Preencha o endereço completo.', 'error');
      return;
    }
    this.step.set('payment');
  }

  goReview() { this.step.set('review'); }

  paymentLabel() {
    return this.payOptions.find(o => o.value === this.paymentMethod)?.title ?? '';
  }

  confirm() {
    this.processing.set(true);
    setTimeout(() => {
      const order = this.orders.create(
        this.cart.items(),
        this.cart.total(),
        { ...this.address },
        this.paymentMethod
      );
      this.cart.clear();
      this.toast.show(`Pedido ${order.id} confirmado!`, 'success');
      this.router.navigate(['/pedidos']);
    }, 900);
  }
}
