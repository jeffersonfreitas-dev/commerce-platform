import { Component, inject } from '@angular/core';
import { ProductService } from '../../core/services/product.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ProductCardCompoment } from '../../shared/components/product-card.compoment/product-card.compoment';

@Component({
  standalone: true,
  imports: [CommonModule, RouterLink, ProductCardCompoment],
  selector: 'app-home',
  styleUrl: './home.scss',
  templateUrl: './home.html',
})
export class HomeComponent {
  products = inject(ProductService);
}
