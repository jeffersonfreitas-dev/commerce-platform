import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { CartService } from '../../../core/services/cart.service';

@Component({
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  selector: 'app-navbar',
  styleUrl: './navbar.compoment.scss',
  templateUrl: './navbar.compoment.html',
})
export class NavbarComponent {
  auth = inject(AuthService);
  cart = inject(CartService);
  private router = inject(Router);

  query = '';
  menuOpen = false;

  onSearch() {
    this.router.navigate(['/produtos'], { queryParams: { q: this.query } });
  }
  toggleMenu() { this.menuOpen = !this.menuOpen; }
  logout() { this.menuOpen = false; this.auth.logout(); }
}
