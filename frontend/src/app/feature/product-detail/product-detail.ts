import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProductService } from '../../core/services/product.service';
import { CartService } from '../../core/services/cart.service';
import { AuthService } from '../../core/services/auth.service';
import { ToastService } from '../../core/services/toast.service';
import { Product } from '../../core/models/product.model';

@Component({
  standalone: true,
  imports: [CommonModule, CurrencyPipe, FormsModule, RouterLink],
  selector: 'app-product-detail',
  styleUrl: './product-detail.scss',
  templateUrl: './product-detail.html',
})
export class ProductDetailComponent {
private route = inject(ActivatedRoute);
  private router = inject(Router);
  private productsSrv = inject(ProductService);
  private cart = inject(CartService);
  private auth = inject(AuthService);
  private toast = inject(ToastService);

  product?: Product;
  selectedImage = '';
  qty = 1;

  constructor() {
    this.route.paramMap.subscribe(p => {
      const id = p.get('id')!;
      this.product = this.productsSrv.getById(id);
      if (this.product) {
        this.selectedImage = this.product.imageUrl;
        this.qty = 1;
      }
    });
  }

  inc() { if (this.product && this.qty < this.product.stock) this.qty++; }
  dec() { if (this.qty > 1) this.qty--; }

  addToCart() {
    if (!this.product) return;
    this.cart.add(this.product, this.qty);
    this.toast.show('Adicionado ao carrinho!', 'success');
  }

  buyNow() {
    if (!this.product) return;
    this.cart.add(this.product, this.qty);
    // comprar agora exige login
    if (!this.auth.isLogged) return this.auth.login();
    this.router.navigate(['/checkout']);
  }
}
