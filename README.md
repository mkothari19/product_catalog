# product_catalog

Use Case:

 

1. User can view products all the products in the catalog. They should be able to also filter based on Product type and/or Promotions applicable.
  Business Logic =om.hcl.business.ProductCatelog
  Test case=com.hcl.busuness.test.CatalogTest

2. User should be able to purchase products which should be persisted with User ID and purchase date. (no need to create user registration just assume a user id)

 Business Logic: com.hcl.entity.ProductPurchase
 Test case: com.hcl.busuness.test.CartTest

Validation rules:

1. New User should get a discount of 10% on total sold products (irrespective of promotion applicable)
Business Logic : com.hcl.business.Cart
Test case: com.hcl.busuness.test.CartTest

2. Existing user purchases products of same Product type check for:
Business Logic : com.hcl.business.Cart
Test case: com.hcl.busuness.test.CartTest

              - If 2 products are selected of same Product Type and Promotion applicable is Y for all selected products,

                then discount should be 10% of total cost of Promotion applicable products

              - If 3 products are selected of same Product Type and Promotion applicable is Y for all selected products,

                then discount should be 20% of total cost of Promotion applicable products
