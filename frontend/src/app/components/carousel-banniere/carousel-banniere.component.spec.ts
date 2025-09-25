import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarouselBanniereComponent } from './carousel-banniere.component';

describe('CarouselBanniereComponent', () => {
  let component: CarouselBanniereComponent;
  let fixture: ComponentFixture<CarouselBanniereComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarouselBanniereComponent]
    });
    fixture = TestBed.createComponent(CarouselBanniereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
