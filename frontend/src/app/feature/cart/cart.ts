import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CartService } from '../../core/services/cart.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  standalone: true,
  imports: [CommonModule, CurrencyPipe, RouterLink],
  selector: 'app-cart',
  styleUrl: './cart.scss',
  templateUrl: './cart.html',
})
export class CartComponent {
  cart = inject(CartService);
  private auth = inject(AuthService);
  private router = inject(Router);

  checkout() {
    if (!this.auth.isLogged) return this.auth.login();
    this.router.navigate(['/checkout']);
  }
}
