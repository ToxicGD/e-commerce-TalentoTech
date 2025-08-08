import { Component, ElementRef, ViewChild, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

interface SocialMedia {
  platform: string;
  url: string;
  icon: string;
  title: string;
  class: string;
}

interface ContactInfo {
  icon: string;
  content: string;
  isMultiline?: boolean;
}

interface QuickLink {
  label: string;
  route: string;
  icon: string;
}

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './footer.html',
  styleUrls: ['./footer.css']
})
export class FooterComponent implements OnInit {
  @ViewChild('footerElement', { static: true }) footerElement!: ElementRef;

  private fb = inject(FormBuilder);
  private router = inject(Router);

  newsletterForm: FormGroup;
  isSubmitting = false;
  submitMessage = '';

  socialMediaLinks: SocialMedia[] = [
    {
      platform: 'Facebook',
      url: 'https://facebook.com',
      icon: 'fab fa-facebook-f',
      title: 'Facebook',
      class: 'facebook'
    },
    {
      platform: 'Instagram',
      url: 'https://instagram.com',
      icon: 'fab fa-instagram',
      title: 'Instagram',
      class: 'instagram'
    },
    {
      platform: 'YouTube',
      url: 'https://youtube.com',
      icon: 'fab fa-youtube',
      title: 'YouTube',
      class: 'youtube'
    },
    {
      platform: 'TikTok',
      url: 'https://tiktok.com',
      icon: 'fab fa-tiktok',
      title: 'TikTok',
      class: 'tiktok'
    },
    {
      platform: 'Twitter',
      url: 'https://twitter.com',
      icon: 'fab fa-twitter',
      title: 'Twitter',
      class: 'twitter'
    },
    {
      platform: 'WhatsApp',
      url: 'https://wa.me/1234567890',
      icon: 'fab fa-whatsapp',
      title: 'WhatsApp',
      class: 'whatsapp'
    },
    {
      platform: 'Email',
      url: 'mailto:contacto@dadosycolores.com',
      icon: 'fas fa-envelope',
      title: 'Email',
      class: 'gmail'
    }
  ];

  quickLinks: QuickLink[] = [
    { label: 'Inicio', route: '/home', icon: 'fas fa-home' },
    { label: 'Acerca de', route: '/about', icon: 'fas fa-info-circle' },
    { label: 'Productos', route: '/products', icon: 'fas fa-cube' },
    { label: 'Galería', route: '/gallery', icon: 'fas fa-images' },
    { label: 'Testimonios', route: '/testimonials', icon: 'fas fa-star' },
    { label: 'Contacto', route: '/contact', icon: 'fas fa-envelope' }
  ];

  productLinks: QuickLink[] = [
    { label: 'Esculturas Artísticas', route: '/products/sculptures', icon: 'fas fa-chess-rook' },
    { label: 'Figuras para Pintar', route: '/products/figures', icon: 'fas fa-paint-brush' },
    { label: 'Impresiones en Resina', route: '/products/resin', icon: 'fas fa-gem' },
    { label: 'Impresiones en PLA', route: '/products/pla', icon: 'fas fa-tools' },
    { label: 'Pedidos Personalizados', route: '/products/custom', icon: 'fas fa-magic' },
    { label: 'Miniaturas Gaming', route: '/products/miniatures', icon: 'fas fa-dice-d20' }
  ];

  contactInfo: ContactInfo[] = [
    {
      icon: 'fas fa-map-marker-alt',
      content: 'Calle Principal 123\nCiudad, País 12345',
      isMultiline: true
    },
    {
      icon: 'fas fa-phone',
      content: '+1 (555) 123-4567'
    },
    {
      icon: 'fas fa-envelope',
      content: 'contacto@dadosycolores.com'
    },
    {
      icon: 'fas fa-clock',
      content: 'Lun - Vie: 9:00 - 18:00\nSáb: 10:00 - 16:00',
      isMultiline: true
    }
  ];

  legalLinks: QuickLink[] = [
    { label: 'Política de Privacidad', route: '/privacy', icon: '' },
    { label: 'Términos y Condiciones', route: '/terms', icon: '' },
    { label: 'Política de Cookies', route: '/cookies', icon: '' },
    { label: 'Devoluciones', route: '/returns', icon: '' },
    { label: 'Envíos', route: '/shipping', icon: '' }
  ];

  constructor() {
    this.newsletterForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit(): void {
    this.setupSmoothScroll();
  }

  // Newsletter form handling
  onNewsletterSubmit(): void {
    if (this.newsletterForm.valid && !this.isSubmitting) {
      this.isSubmitting = true;
      const email = this.newsletterForm.get('email')?.value;

      // Simular llamada a API
      setTimeout(() => {
        this.submitMessage = `¡Gracias por suscribirte con el email: ${email}!`;
        this.newsletterForm.reset();
        this.isSubmitting = false;

        // Limpiar mensaje después de 5 segundos
        setTimeout(() => {
          this.submitMessage = '';
        }, 5000);
      }, 1000);
    } else {
      // Marcar todos los campos como touched para mostrar errores
      this.newsletterForm.markAllAsTouched();
    }
  }

  // Social media click tracking
  onSocialMediaClick(socialMedia: SocialMedia, event: Event): void {
    console.log(`Clic en: ${socialMedia.platform}`);

    // Analytics tracking aquí si es necesario
    // this.analytics.track('social_media_click', { platform: socialMedia.platform });

    // Abrir en nueva ventana
    if (socialMedia.url.startsWith('http')) {
      event.preventDefault();
      window.open(socialMedia.url, '_blank', 'noopener,noreferrer');
    }
  }

  // Navigation handling
  navigateTo(route: string, event: Event): void {
    event.preventDefault();

    if (route.startsWith('#')) {
      // Smooth scroll para anchors
      this.smoothScrollTo(route.substring(1));
    } else {
      // Navegación con Router
      this.router.navigate([route]);
    }
  }

  // Smooth scroll implementation
  private setupSmoothScroll(): void {
    // Ya no necesitamos esto en Angular, lo manejamos con el método navigateTo
  }

  private smoothScrollTo(elementId: string): void {
    const element = document.getElementById(elementId);
    if (element) {
      element.scrollIntoView({
        behavior: 'smooth',
        block: 'start'
      });
    }
  }

  // Getter para facilitar el acceso al control del email
  get emailControl() {
    return this.newsletterForm.get('email');
  }

  // Método para obtener errores del email
  getEmailError(): string {
    const emailControl = this.emailControl;
    if (emailControl?.hasError('required')) {
      return 'El email es requerido';
    }
    if (emailControl?.hasError('email')) {
      return 'Ingresa un email válido';
    }
    return '';
  }

  // Método para formatear contenido multilinea
  formatMultilineContent(content: string): string[] {
    return content.split('\n');
  }

  // Método para el año actual
  getCurrentYear(): number {
    return new Date().getFullYear();
  }
}