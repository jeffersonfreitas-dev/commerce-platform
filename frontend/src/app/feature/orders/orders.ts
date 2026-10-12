import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { OrderService } from '../../core/services/order.service';

@Component({
  standalone: true,
  imports: [CommonModule, CurrencyPipe, DatePipe, RouterLink],
  selector: 'app-orders',
  styleUrl: './orders.scss',
  templateUrl: './orders.html',
})
export class OrdersComponent {
  orders = inject(OrderService);

  statusLabel(s: string) {
    const map: Record<string,string> = {
      PENDING: 'Pendente', PAID: 'Pago', SHIPPED: 'Enviado',
      DELIVERED: 'Entregue', CANCELED: 'Cancelado'
    };
    return map[s] ?? s;
  }
  paymentLabel(p: string) {
    const map: Record<string,string> = {
      PIX: 'PIX', CREDIT_CARD: 'Cartão de crédito', BOLETO: 'Boleto'
    };
    return map[p] ?? p;
  }
}
