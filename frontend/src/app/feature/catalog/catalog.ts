import { CommonModule } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProductCardCompoment } from '../../shared/components/product-card.compoment/product-card.compoment';
import { ProductService } from '../../core/services/product.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, ProductCardCompoment],
  selector: 'app-catalog',
  styleUrl: './catalog.scss',
  templateUrl: './catalog.html',
})
export class CatalogComponent {
  products = inject(ProductService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  query = '';
  category = '';
  sortBy: 'new'|'price-asc'|'price-desc'|'rating' = 'new';

  private trigger = signal(0);

  filtered = computed(() => {
    this.trigger(); // dependência de reavaliação
    const list = this.products.search(this.query, this.category);
    const sorted = [...list];
    switch (this.sortBy) {
      case 'price-asc':  sorted.sort((a,b) => a.price - b.price); break;
      case 'price-desc': sorted.sort((a,b) => b.price - a.price); break;
      case 'rating':     sorted.sort((a,b) => b.rating - a.rating); break;
      default:           sorted.sort((a,b) => b.createdAt.localeCompare(a.createdAt));
    }
    return sorted;
  });

  constructor() {
    // sincroniza com query params
    this.route.queryParamMap.subscribe(params => {
      this.query = params.get('q') ?? '';
      this.category = params.get('cat') ?? '';
      this.applyQuery();
    });
  }

  applyQuery() {
    this.trigger.update(v => v + 1);
    this.router.navigate([], {
      queryParams: {
        q: this.query || null,
        cat: this.category || null,
      },
      queryParamsHandling: 'merge',
      replaceUrl: true,
    });
  }
}
