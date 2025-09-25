import { Component, OnInit, ViewChildren, QueryList, ElementRef, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit, AfterViewInit {
  carouselItems!: QueryList<ElementRef>;

  @ViewChildren('carouselItem') carouselItemsQueryList!: QueryList<ElementRef>;

  constructor() { }

  ngOnInit(): void {
    // L'initialisation peut être faite après la vue a été initialisée
  }

  ngAfterViewInit(): void {
    this.carouselItems = this.carouselItemsQueryList;
    this.adjustCarouselItems();
  }

  adjustCarouselItems(): void {
    if (this.carouselItems && this.carouselItems.length > 0) {
      this.carouselItems.forEach((el) => {
        // Votre logique pour ajuster les éléments du carrousel ici
      });
    }
  }
}
